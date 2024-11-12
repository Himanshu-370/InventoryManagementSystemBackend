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
#### Basic CRUD
- `GET    /api/categories`                  - List all categories
- `GET    /api/categories/{id}`             - Get specific category
- `POST   /api/categories`                  - Create new category
- `PUT    /api/categories/{id}`             - Update category
- `DELETE /api/categories/{id}`             - Delete category

#### Relationship endpoints
- `GET    /api/categories/{id}/products`    - Get products in category
- `POST   /api/categories/{id}/products`    - Add products to category
- `DELETE /api/categories/{id}/products/{productId}`  - Remove product from category

### Products
#### Basic CRUD
- `GET    /api/products`                    - List all products
- `GET    /api/products/{id}`              - Get specific product
- `POST   /api/products`                   - Create new product
- `PUT    /api/products/{id}`              - Update product
- `DELETE /api/products/{id}`              - Delete product

#### Relationship endpoints
- `GET    /api/products/{id}/subcategories`     - Get subcategories of product
- `POST   /api/products/{id}/subcategories`     - Add subcategories to product
- `DELETE /api/products/{id}/subcategories/{subcategoryId}`  - Remove subcategory from product

### Subcategories
#### Basic CRUD
- `GET    /api/subcategories`              - List all subcategories
- `GET    /api/subcategories/{id}`         - Get specific subcategory
- `POST   /api/subcategories`              - Create new subcategory
- `PUT    /api/subcategories/{id}`         - Update subcategory
- `DELETE /api/subcategories/{id}`         - Delete subcategory

#### Relationship endpoints
- `GET    /api/subcategories/{id}/materials`    - Get materials in subcategory
- `POST   /api/subcategories/{id}/materials`    - Add materials to subcategory
- `DELETE /api/subcategories/{id}/materials/{materialId}`  - Remove material from subcategory


### Raw Materials
#### Basic CRUD
- `GET    /api/materials`                  - List all materials
- `GET    /api/materials/{id}`             - Get specific material
- `POST   /api/materials`                  - Create new material
- `PUT    /api/materials/{id}`             - Update material
- `DELETE /api/materials/{id}`             - Delete material

---

## Usage Examples

#### Creating a New Category
```bash
curl -X POST http://localhost:8080/api/categories \
-H "Content-Type: application/json" \
-d '{
  "name": "Emulsions"
}'
```
#### Creating a New Raw Material
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