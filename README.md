# 🛍️ Inditex Product Sorting System

Sistema de gestión y ordenación de productos para Inditex que permite administrar productos y ordenarlos según diferentes criterios.

## 🚀 Características

- **Product Service**: Gestión de productos (CRUD)
- **Sorting Service**: Ordenación de productos por diferentes criterios
- **API Gateway**: Punto de entrada unificado para todos los servicios (Esquema ficheros, por cumplimentar)
- **Docker Compose**: Despliegue fácil con contenedores
- **Base de datos PostgreSQL**: Almacenamiento persistente en docker
- **Flyway**: Migraciones de base de datos, en este caso para inicialización de las tablas con los registros iniciales

## 🛠️ Requisitos

- Java 21
- Maven 3.8+
- Docker 20.10+
- Docker Compose 2.0+

## 🚀 Inicio Rápido

### 1. Clonar el repositorio

```bash
git clone [URL_DEL_REPOSITORIO]
cd inditex-product-sorting
```

### 2. Iniciar los servicios con Docker Compose

```bash
docker-compose up -d --build
```

### 3. Verificar que los servicios estén en ejecución

```bash
docker-compose ps
```

## 🌐 Servicios y Puertos

| Servicio | URL | Puerto | Descripción |
|----------|-----|--------|-------------|
| Product Service | http://localhost:8081 | 8081 | Gestión de productos |
| Sorting Service | http://localhost:8082 | 8082 | Ordenación de productos |
| PostgreSQL | localhost:5433: 5434 Base de datos |

## 🗄️ Base de Datos

Se crean automáticamente dos bases de datos PostgreSQL en docker

- **productdb**: Para el servicio de productos (puerto 5433)
- **sortingdb**: Para el servicio de ordenación (puerto 5434)

**Credenciales por defecto:**
- Usuario: `postgres`
- Contraseña: `postgres`

## 🛠️ Desarrollo

### Estructura del Proyecto

```
inditex-product-sorting/
├── api-gateway/          # API Gateway (Spring Cloud Gateway) - Esqueleto
├── product-service/      # Servicio de gestión de productos
├── sorting-service/      # Servicio de ordenación de productos
└── docker-compose.yml    # Configuración de Docker Compose
```

### Para ejecutar los servicios sin Docker se deben realizar cambios en los application.yml, se ha pensado en el desarrollo con docker-compose para tener un mayor control de los servicios.

### Sorting Service

- **SortingController**: http://localhost:8082/api/sort

```http
POST http://localhost:8082/api/sort
Content-Type: application/json

{
  "productIds": [1, 2, 3],
  "criteriaWeights": {
    "sales": {
      "weight": 0.7,
      "params": {
        "order": "desc"
      }
    },
    "stockRatio": {
      "weight": 0.3,
      "params": {
        "order": "desc"
      }
    }
  }
}
```
#### Criterios de ordenación disponibles

Puedes obtener la lista de criterios disponibles haciendo una petición GET a:

```http
GET http://localhost:8082/api/sort/criteria
```

#### ProductController

GET    /api/products           - Obtener todos los productos
GET    /api/products/{id}      - Obtener un producto por ID
GET    /api/products/ids       - Obtener múltiples productos por IDs
POST   /api/products           - Crear un nuevo producto
PUT    /api/products/{id}      - Actualizar un producto existente
DELETE /api/products/{id}      - Eliminar un producto

## 🔄 Variables de Entorno

Puedes configurar los servicios mediante variables de entorno. Algunas importantes:

- `SPRING_DATASOURCE_URL`: URL de conexión a la base de datos
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos
- `SERVER_PORT`: Puerto del servidor

## 🐛 Solución de Problemas

### Ver logs de los contenedores

```bash
# Ver logs de todos los servicios
docker-compose logs -f

# Ver logs de un servicio específico
docker-compose logs -f product-service
```

### Reconstruir y reiniciar un servicio

```bash
docker-compose up -d --build --no-deps product-service
```

