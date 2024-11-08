# Inventory Management System

A Spring Boot application designed to efficiently manage product inventory, raw materials, and formulations for chemical and paint product manufacturing.

---

## Features
- **Product and Category Management**
- **Raw Material Inventory Tracking**
- **Product Formulation Management**
- **Advanced Search & Filtering**
- **Cost Calculation Automation**
- **JSON-based Data Storage**

---

## Prerequisites
- **Java JDK 17** or higher
- **Maven 3.6** or higher
- **Git** (optional, for version control)

---

## Project Setup

1. **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd inventory-management
    ```

2. **Create data directory and files**:
    ```bash
    mkdir src/main/resources/data
    touch src/main/resources/data/categories.json
    touch src/main/resources/data/products.json
    touch src/main/resources/data/rawmaterials.json
    touch src/main/resources/data/formulations.json
    ```

3. **Build the project**:
    ```bash
    mvn clean install
    ```

---

## API Endpoints

### Categories
- `GET /api/categories` - Retrieve all categories
- `GET /api/categories/{id}` - Retrieve a category by ID
- `POST /api/categories` - Create a new category
- `PUT /api/categories/{id}` - Update an existing category

### Products
- `GET /api/products` - Retrieve all products
- `GET /api/products/{id}` - Retrieve a product by ID
- `GET /api/products/category/{categoryId}` - Retrieve products by category
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update an existing product

### Raw Materials
- `GET /api/rawmaterials` - Retrieve all raw materials
- `GET /api/rawmaterials/{id}` - Retrieve a raw material by ID
- `POST /api/rawmaterials` - Create a new raw material
- `PUT /api/rawmaterials/{id}` - Update an existing raw material

### Search
- `GET /api/search/products/advanced` - Perform an advanced search for products
- `GET /api/search/rawmaterials/advanced` - Perform an advanced search for raw materials

---

## Usage Examples

### Creating a New Category
```bash
curl -X POST http://localhost:8080/api/categories \
-H "Content-Type: application/json" \
-d '{
  "name": "Emulsions"
}'
```
### Creating a New Raw Material
```bash
curl -X POST http://localhost:8080/api/rawmaterials \
-H "Content-Type: application/json" \
-d '{
  "name": "Ethanol",
  "quantity": 100,
  "unit": "kg",
  "price": 25.0,
  "density": 0.789
}'
```

### Creating a New Product with Formulation
```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{
  "name": "Paint A",
  "categoryId": 1,
  "formulations": [
    {
      "rawMaterialId": 1,
      "quantity": 10.5
    }
  ]
}'

```

### Advanced Search Example
```bash
curl "http://localhost:8080/api/search/products/advanced?\
query=Paint&\
minPrice=10&\
maxPrice=100&\
categoryIds=1&\
sortBy=price&\
sortDirection=desc"
```

## Data Models
###Category

```json
{
  "id": 1,
  "name": "Emulsions"
}
```

### Raw Material
```json
{
  "id": 1,
  "name": "Ethanol",
  "quantity": 100,
  "unit": "kg",
  "price": 25.0,
  "density": 0.789,
  "priceAfterConversion": 26.25
}
```

### Product
```json
{
  "id": 1,
  "name": "Paint A",
  "categoryId": 1,
  "totalCost": 156.75,
  "formulations": [
    {
      "rawMaterialId": 1,
      "quantity": 10.5,
      "price": 25.0,
      "priceAfterConversion": 26.25
    }
  ]
}
```

## Data Storage
All data is stored in JSON files located in 
```
src/main/resources/data/
```

- categories.json
- products.json
- rawmaterials.json
- formulations.json