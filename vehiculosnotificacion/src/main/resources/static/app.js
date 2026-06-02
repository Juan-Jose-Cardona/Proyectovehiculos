let API_URL = "http://localhost:8080";

let currentModule = null;
let currentAction = null;
let currentRecord = null;
let SWAGGER_PATHS = {};
let LOOKUP_CACHE = {};

const MODULES = [
    {
        name: "usuarios",
        title: "Usuarios",
        subtitle: "gestión de usuarios",
        basePath: "/users",
        resultId: "usersResult",
        routes: {},
        fields: [
            {name: "email", type: "email"},
            {name: "phone", type: "text"},
            {name: "password", type: "password"},
            {name: "fullName", type: "text"},
            {name: "timezone", type: "text"},
            {name: "isActive", type: "boolean"}
        ]
    },
    {
        name: "vehiculos",
        title: "Vehículos",
        subtitle: "gestión de vehículos",
        basePath: "/vehicles",
        resultId: "vehiclesResult",
        routes: {},
        fields: [
            {name: "userId", type: "lookup", moduleName: "usuarios"},
            {name: "plate", type: "text"},
            {name: "brand", type: "text"},
            {name: "lineModel", type: "text"},
            {name: "modelYear", type: "number"},
            {name: "vehicleType", type: "text"},
            {name: "notes", type: "textarea"}
        ]
    },
    {
        name: "obligaciones",
        title: "Obligaciones",
        subtitle: "gestión de obligaciones",
        basePath: "/obligations",
        resultId: "obligationsResult",
        routes: {},
        fields: [
            {name: "vehicleId", type: "lookup", moduleName: "vehiculos"},
            {name: "type", type: "select", values: ["SOAT", "RTP", "IMPUESTO_VALLE"]},
            {name: "dueDate", type: "date"},
            {name: "status", type: "select", values: ["VIGENTE", "VENCIDO", "POR_VENCER", "PAGADA"]},
            {name: "lastCalcAt", type: "datetime-local"},
            {name: "notes", type: "textarea"}
        ]
    },
    {
        name: "reglas",
        title: "Reglas",
        subtitle: "gestión de reglas",
        basePath: "/obligation-rules",
        resultId: "obligationRulesResult",
        routes: {},
        fields: [
            {name: "obligationId", type: "lookup", moduleName: "obligaciones"},
            {name: "sendWindowStart", type: "time"},
            {name: "sendWindowEnd", type: "time"},
            {name: "isEnabled", type: "boolean"}
        ]
    },
    {
        name: "canales",
        title: "Canales de reglas",
        subtitle: "gestión de canales",
        basePath: "/obligation-rule-channels",
        resultId: "obligationRuleChannelsResult",
        routes: {},
        fields: [
            {name: "obligationRuleId", type: "lookup", moduleName: "reglas"},
            {name: "channel", type: "select", values: ["EMAIL", "SMS"]}
        ]
    },
    {
        name: "dias",
        title: "Días de notificación",
        subtitle: "gestión de días",
        basePath: "/obligation-rule-notify-days",
        resultId: "obligationRuleNotifyDaysResult",
        routes: {},
        fields: [
            {name: "obligationRuleId", type: "lookup", moduleName: "reglas"},
            {name: "notifyDay", type: "number"}
        ]
    },
    {
        name: "notificaciones",
        title: "Notificaciones",
        subtitle: "gestión de notificaciones",
        basePath: "/notifications",
        resultId: "notificationsResult",
        routes: {},
        fields: [
            {name: "userId", type: "lookup", moduleName: "usuarios"},
            {name: "vehicleId", type: "lookup", moduleName: "vehiculos"},
            {name: "obligationId", type: "lookup", moduleName: "obligaciones"},
            {name: "channel", type: "select", values: ["EMAIL", "SMS"]},
            {name: "kind", type: "select", values: ["DUE_IN_DAYS", "DUE_TODAY", "OVERDUE"]},
            {name: "daysBeforeDue", type: "number"},
            {name: "dueDate", type: "date"},
            {name: "scheduledFor", type: "datetime-local"},
            {name: "payloadJson", type: "textarea"},
            {name: "status", type: "select", values: ["PENDING", "PROCESSING", "SENT", "FAILED", "CANCELLED"]},
            {name: "attemptCount", type: "number"},
            {name: "lastError", type: "textarea"},
            {name: "lockedBy", type: "text"},
            {name: "lockedAt", type: "datetime-local"},
            {name: "sentAt", type: "datetime-local"}
        ]
    },
    {
        name: "intentos",
        title: "Intentos",
        subtitle: "gestión de intentos",
        basePath: "/notification-attempts",
        resultId: "notificationAttemptsResult",
        routes: {},
        fields: [
            {name: "notificationId", type: "lookup", moduleName: "notificaciones"},
            {name: "attemptNo", type: "number"},
            {name: "provider", type: "text"},
            {name: "requestMeta", type: "textarea"},
            {name: "responseMeta", type: "textarea"},
            {name: "success", type: "boolean"},
            {name: "errorMessage", type: "textarea"}
        ]
    },
    {
        name: "auditoria",
        title: "Auditoría",
        subtitle: "gestión de auditoría",
        basePath: "/audit-logs",
        resultId: "auditLogsResult",
        routes: {},
        fields: [
            {name: "userId", type: "lookup", moduleName: "usuarios"},
            {name: "entityType", type: "text"},
            {name: "entityId", type: "number"},
            {name: "action", type: "select", values: ["CREATE", "UPDATE", "DELETE", "LOGIN", "PASSWORD_RESET", "CONFIG_CHANGE"]},
            {name: "beforeJson", type: "textarea"},
            {name: "afterJson", type: "textarea"},
            {name: "ip", type: "text"},
            {name: "userAgent", type: "textarea"}
        ]
    }
];

//inicia interfaz principal
document.addEventListener("DOMContentLoaded", startApp);

//configura vista inicial
function startApp() {
    loadOpenApiModules()
        .then(() => {
            renderMenu();
            renderDashboard();
        })
        .catch(() => {
            renderMenu();
            renderDashboard();
        });
}

//carga rutas desde swagger
function loadOpenApiModules() {
    API_URL = document.getElementById("apiUrl").value;

    return fetch(API_URL + "/v3/api-docs")
        .then(response => response.json())
        .then(openApi => buildModulesFromOpenApi(openApi));
}

//construye modulos desde swagger
function buildModulesFromOpenApi(openApi) {
    SWAGGER_PATHS = openApi.paths;

    MODULES.forEach(module => {
        module.routes = findCrudRoutes(module.basePath);
    });
}

//busca rutas crud del modulo
function findCrudRoutes(basePath) {
    return {
        getAll: findPath(basePath, "/all", "get"),
        getById: findPath(basePath, "/{id}", "get"),
        create: findPath(basePath, "/create", "post"),
        update: findPath(basePath, "/update/{id}", "put"),
        delete: findPath(basePath, "/delete/{id}", "delete")
    };
}

//encuentra ruta por metodo
function findPath(basePath, suffix, method) {
    const fullPath = basePath + suffix;

    if (SWAGGER_PATHS[fullPath] && SWAGGER_PATHS[fullPath][method]) {
        return fullPath;
    }

    return null;
}

//pinta menu lateral
function renderMenu() {
    const container = document.getElementById("menuContainer");
    container.innerHTML = "";

    MODULES.forEach(module => {
        const button = document.createElement("button");
        button.className = "menu-item";
        button.textContent = module.title;
        button.onclick = () => renderModule(module);
        container.appendChild(button);
    });
}

//pinta dashboard principal
function renderDashboard() {
    currentModule = null;
    setActiveMenu("dashboard");

    const content = document.getElementById("mainContent");

    content.innerHTML = `
        <div class="page-header">
            <div>
                <h2>Dashboard</h2>
                <p>resumen general del sistema</p>
            </div>
        </div>

        <section class="dashboard-grid" id="dashboardGrid"></section>

        <section class="welcome-card">
            <h2>bienvenido al sistema de gestión [Este Bloque No Hace Nada]</h2>
            <p>esta aplicación consume rutas desde swagger openapi.</p>

            <div class="help-list">
                <span>Boton</span>
                <span>Boton</span>
                <span>Boton</span>
                <span>Boton</span>
            </div>
        </section>
    `;

    loadDashboardCounts();
}

//carga conteos del dashboard
function loadDashboardCounts() {
    const grid = document.getElementById("dashboardGrid");

    MODULES.slice(0, 4).forEach(module => {
        if (!module.routes.getAll) {
            grid.appendChild(createSummaryCard(module.title, 0));
            return;
        }

        requestData(module.routes.getAll, "GET", null)
            .then(data => {
                const count = Array.isArray(data) ? data.length : 0;
                grid.appendChild(createSummaryCard(module.title, count));
            })
            .catch(() => {
                grid.appendChild(createSummaryCard(module.title, 0));
            });
    });
}

//crea tarjeta resumen
function createSummaryCard(title, count) {
    const card = document.createElement("article");
    card.className = "summary-card";

    card.innerHTML = `
        <div class="summary-icon">${title.substring(0, 2)}</div>
        <div>
            <h3>${count}</h3>
            <p>${title}</p>
        </div>
    `;

    return card;
}

//pinta modulo seleccionado
function renderModule(module) {
    currentModule = module;
    setActiveMenu(module.title);

    const content = document.getElementById("mainContent");

    content.innerHTML = `
        <div class="page-header">
            <div>
                <h2>${module.title}</h2>
                <p>${module.subtitle}</p>
            </div>

            <button onclick="openCreateModal()">+ agregar</button>
        </div>

        <div class="search-row">
            <input id="searchId" type="number" placeholder="buscar por id">
            <button onclick="loadById()">buscar</button>
            <button class="secondary-button" onclick="loadAll()">cargar todos</button>
        </div>

        <div id="${module.resultId}" class="table-card"></div>
        <div id="moduleMessage" class="message"></div>
    `;

    loadAll();
}

//activa opcion del menu
function setActiveMenu(title) {
    const items = document.querySelectorAll(".menu-item");

    items.forEach(item => {
        item.classList.remove("active");

        if (item.textContent === title) {
            item.classList.add("active");
        }

        if (title === "dashboard" && item.textContent === "dashboard") {
            item.classList.add("active");
        }
    });
}

//carga todos los registros
function loadAll() {
    if (!currentModule.routes.getAll) {
        showMessage("ruta get all no encontrada en swagger", true);
        return;
    }

    requestData(currentModule.routes.getAll, "GET", null)
        .then(data => renderTable(data))
        .catch(error => showMessage(error.message, true));
}

//busca registro por id
function loadById() {
    const id = document.getElementById("searchId").value;

    if (!id) {
        showMessage("debe ingresar un id", true);
        return;
    }

    if (!currentModule.routes.getById) {
        showMessage("ruta get by id no encontrada en swagger", true);
        return;
    }

    const path = currentModule.routes.getById.replace("{id}", id);

    requestData(path, "GET", null)
        .then(data => renderTable([data]))
        .catch(error => showMessage(error.message, true));
}

//abre modal crear
function openCreateModal() {
    currentAction = "create";
    currentRecord = null;
    openModal(`crear ${currentModule.title}`, {});
}

//abre modal editar
function openEditModal(id) {
    if (!currentModule.routes.getById) {
        showMessage("ruta get by id no encontrada en swagger", true);
        return;
    }

    const path = currentModule.routes.getById.replace("{id}", id);

    requestData(path, "GET", null)
        .then(data => {
            currentAction = "update";
            currentRecord = data;
            openModal(`editar ${currentModule.title}`, data);
        })
        .catch(error => showMessage(error.message, true));
}

//abre ventana modal
function openModal(title, data) {
    document.getElementById("modalTitle").textContent = title;
    document.getElementById("modalBody").innerHTML = createForm(data);
    document.getElementById("modalOverlay").classList.remove("hidden");
    loadLookupOptions(data);
}

//cierra ventana modal
function closeModal() {
    document.getElementById("modalOverlay").classList.add("hidden");
}

//crea formulario dinamico
function createForm(data) {
    return currentModule.fields
        .map(field => createField(field, data[field.name]))
        .join("");
}

//crea campo del formulario
function createField(field, value) {
    const fieldId = `field_${field.name}`;
    const safeValue = value === null || value === undefined ? "" : value;

    if (field.type === "lookup") {
        return `
            <div class="field">
                <label for="${fieldId}">${field.name}</label>
                <select id="${fieldId}" data-value="${safeValue}">
                    <option value="">cargando opciones</option>
                </select>
                <small>seleccione un registro existente</small>
            </div>
        `;
    }

    if (field.type === "textarea") {
        return `
            <div class="field">
                <label for="${fieldId}">${field.name}</label>
                <textarea id="${fieldId}">${safeValue}</textarea>
            </div>
        `;
    }

    if (field.type === "select") {
        return `
            <div class="field">
                <label for="${fieldId}">${field.name}</label>
                <select id="${fieldId}">
                    <option value="">seleccione</option>
                    ${field.values.map(item => createOption(item, safeValue)).join("")}
                </select>
            </div>
        `;
    }

    if (field.type === "boolean") {
        return `
            <div class="field">
                <label for="${fieldId}">${field.name}</label>
                <select id="${fieldId}">
                    <option value="">seleccione</option>
                    <option value="true" ${safeValue === true ? "selected" : ""}>true</option>
                    <option value="false" ${safeValue === false ? "selected" : ""}>false</option>
                </select>
            </div>
        `;
    }

    return `
        <div class="field">
            <label for="${fieldId}">${field.name}</label>
            <input id="${fieldId}" type="${field.type}" value="${safeValue}">
        </div>
    `;
}

//carga listas relacionadas
function loadLookupOptions(data) {
    const lookupFields = currentModule.fields.filter(field => field.type === "lookup");

    lookupFields.forEach(field => {
        const selectedValue = data[field.name];
        loadLookupField(field, selectedValue);
    });
}

//carga campo relacionado
function loadLookupField(field, selectedValue) {
    const targetModule = findModuleByName(field.moduleName);
    const fieldElement = document.getElementById(`field_${field.name}`);

    if (!targetModule || !targetModule.routes.getAll) {
        fieldElement.innerHTML = "<option value=\"\">sin ruta disponible</option>";
        return;
    }

    getLookupData(targetModule)
        .then(data => fillLookupField(fieldElement, data, selectedValue, targetModule))
        .catch(() => {
            fieldElement.innerHTML = "<option value=\"\">error cargando opciones</option>";
        });
}

//obtiene datos relacionados
function getLookupData(module) {
    if (LOOKUP_CACHE[module.name]) {
        return Promise.resolve(LOOKUP_CACHE[module.name]);
    }

    return requestData(module.routes.getAll, "GET", null)
        .then(data => {
            LOOKUP_CACHE[module.name] = Array.isArray(data) ? data : [];
            return LOOKUP_CACHE[module.name];
        });
}

//llena campo relacionado
function fillLookupField(select, data, selectedValue, module) {
    let html = "<option value=\"\">seleccione</option>";

    data.forEach(item => {
        const selected = Number(selectedValue) === Number(item.id) ? "selected" : "";
        html += `<option value="${item.id}" ${selected}>${getLookupLabel(module.name, item)}</option>`;
    });

    select.innerHTML = html;
}

//crea etiqueta relacionada
function getLookupLabel(moduleName, item) {
    if (moduleName === "usuarios") {
        return `${item.id} - ${item.fullName || item.email || item.phone}`;
    }

    if (moduleName === "vehiculos") {
        return `${item.id} - ${item.plate || item.brand || "vehiculo"}`;
    }

    if (moduleName === "obligaciones") {
        return `${item.id} - ${item.type || "obligacion"} ${item.dueDate || ""}`;
    }

    if (moduleName === "reglas") {
        return `${item.id} - regla ${item.obligationId || ""}`;
    }

    if (moduleName === "notificaciones") {
        return `${item.id} - ${item.kind || "notificacion"} ${item.status || ""}`;
    }

    return `${item.id}`;
}

//busca modulo por nombre
function findModuleByName(name) {
    return MODULES.find(module => module.name === name);
}

//crea opcion seleccionable
function createOption(item, value) {
    const selected = item === value ? "selected" : "";
    return `<option value="${item}" ${selected}>${item}</option>`;
}

//guarda registro actual
function saveRecord() {
    const body = collectBody();

    if (currentAction === "create") {
        saveCreate(body);
        return;
    }

    saveUpdate(body);
}

//guarda nuevo registro
function saveCreate(body) {
    if (!currentModule.routes.create) {
        showMessage("ruta create no encontrada en swagger", true);
        return;
    }

    requestData(currentModule.routes.create, "POST", body)
        .then(() => afterSave())
        .catch(error => showMessage(error.message, true));
}

//actualiza registro existente
function saveUpdate(body) {
    if (!currentModule.routes.update) {
        showMessage("ruta update no encontrada en swagger", true);
        return;
    }

    const path = currentModule.routes.update.replace("{id}", currentRecord.id);

    requestData(path, "PUT", body)
        .then(() => afterSave())
        .catch(error => showMessage(error.message, true));
}

//procesa guardado exitoso
function afterSave() {
    LOOKUP_CACHE = {};
    closeModal();
    showMessage("operación realizada correctamente", false);
    loadAll();
}

//elimina registro por id
function deleteRecord(id) {
    const confirmDelete = confirm("¿desea eliminar este registro?");

    if (!confirmDelete) {
        return;
    }

    if (!currentModule.routes.delete) {
        showMessage("ruta delete no encontrada en swagger", true);
        return;
    }

    const path = currentModule.routes.delete.replace("{id}", id);

    requestData(path, "DELETE", null)
        .then(data => {
            LOOKUP_CACHE = {};
            showMessage(typeof data === "string" ? data : "registro eliminado correctamente", false);
            loadAll();
        })
        .catch(error => showMessage(error.message, true));
}

//recoge datos del formulario
function collectBody() {
    const body = {};

    currentModule.fields.forEach(field => {
        const input = document.getElementById(`field_${field.name}`);
        const value = input.value;

        if (value !== "") {
            body[field.name] = parseValue(value, field.type);
        }
    });

    return body;
}

//convierte valor segun tipo
function parseValue(value, type) {
    if (type === "number" || type === "lookup") {
        return Number(value);
    }

    if (type === "boolean") {
        return value === "true";
    }

    return value;
}

//pinta tabla de registros
function renderTable(data) {
    const container = document.getElementById(currentModule.resultId);

    if (!Array.isArray(data) || data.length === 0) {
        container.innerHTML = "<p>no hay datos registrados</p>";
        return;
    }

    const columns = Object.keys(data[0]);

    let html = "<table><thead><tr>";

    columns.forEach(column => {
        html += `<th>${column}</th>`;
    });

    html += "<th>acciones</th></tr></thead><tbody>";

    data.forEach(item => {
        html += "<tr>";

        columns.forEach(column => {
            html += `<td>${formatValue(column, item[column])}</td>`;
        });

        html += `
            <td>
                <div class="action-buttons">
                    <button class="edit-button" onclick="openEditModal(${item.id})">editar</button>
                    <button class="delete-button" onclick="deleteRecord(${item.id})">eliminar</button>
                </div>
            </td>
        `;

        html += "</tr>";
    });

    html += "</tbody></table>";

    container.innerHTML = html;
}

//formatea valores de tabla
function formatValue(column, value) {
    if (value === null || value === undefined) {
        return "";
    }

    if (column === "id") {
        return `<span class="id-badge">#${value}</span>`;
    }

    if (typeof value === "object") {
        return JSON.stringify(value);
    }

    return value;
}

//consulta datos del backend
function requestData(path, method, body) {
    API_URL = document.getElementById("apiUrl").value;

    const options = {
        method: method,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (body !== null) {
        options.body = JSON.stringify(body);
    }

    return fetch(API_URL + path, options)
        .then(response => handleResponse(response));
}

//procesa respuesta http
function handleResponse(response) {
    return response.text().then(text => {
        const data = parseResponse(text);

        if (!response.ok) {
            const message = data && data.message ? data.message : "error al consultar";
            throw new Error(message);
        }

        return data;
    });
}

//convierte respuesta del backend
function parseResponse(text) {
    if (!text) {
        return null;
    }

    try {
        return JSON.parse(text);
    } catch (error) {
        return text;
    }
}

//verifica conexion backend
function verifyConnection() {
    API_URL = document.getElementById("apiUrl").value;
    const status = document.getElementById("connectionStatus");

    fetch(API_URL + "/v3/api-docs")
        .then(response => {
            if (!response.ok) {
                throw new Error("sin conexión");
            }

            return response.json();
        })
        .then(openApi => {
            buildModulesFromOpenApi(openApi);
            LOOKUP_CACHE = {};
            status.textContent = "conectado";
            status.className = "status ok";
        })
        .catch(() => {
            status.textContent = "sin conexión";
            status.className = "status error";
        });
}

//muestra mensaje del modulo
function showMessage(message, isError) {
    const container = document.getElementById("moduleMessage");

    if (!container) {
        return;
    }

    container.textContent = message;
    container.className = isError ? "message error" : "message success";
}
