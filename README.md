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
- `GET    /api/categories`                  - List all categories  - Done
- `GET    /api/categories/{id}`             - Get specific category  - Done
- `POST   /api/categories`                  - Create new category  - Done
- `PUT    /api/categories/{id}`             - Update category  - Done
- `DELETE /api/categories/{id}`             - Delete category  - Done

#### Relationship endpoints
- `GET    /api/categories/{id}/products`    - Get products in category  - Done
- `POST   /api/categories/{id}/products`    - Add products to category  - Done
- `DELETE /api/categories/{id}/products/{productId}`  - Remove product from category  - Done

### Products
#### Basic CRUD
- `GET    /api/products`                    - List all products  - Done
- `GET    /api/products/{id}`              - Get specific product  - Done
- `POST   /api/products`                   - Create new product  - Done
- `PUT    /api/products/{id}`              - Update product  - Done
- `DELETE /api/products/{id}`              - Delete product  - Done

#### Relationship endpoints
- `GET    /api/products/{id}/subcategories`     - Get subcategories of product - Done
- `POST   /api/products/{id}/subcategories`     - Add subcategories to product - Done
- `DELETE /api/products/{id}/subcategories/{subcategoryId}`  - Remove subcategory from product - Done

### Subcategories
#### Basic CRUD
- `GET    /api/subcategories`              - List all subcategories - Done
- `GET    /api/subcategories/{id}`         - Get specific subcategory - Done
- `POST   /api/subcategories`              - Create new subcategory - Done
- `PUT    /api/subcategories/{id}`         - Update subcategory - Done
- `DELETE /api/subcategories/{id}`         - Delete subcategory - Done

#### Relationship endpoints
- `GET    /api/subcategories/{id}/materials`    - Get materials in subcategory
- `POST   /api/subcategories/{id}/materials`    - Add materials to subcategory
- `DELETE /api/subcategories/{id}/materials/{materialId}`  - Remove material from subcategory


### Raw Materials
#### Basic CRUD
- `GET    /api/rawmaterials`                  - List all rawmaterials
- `GET    /api/rawmaterials/{id}`             - Get specific material
- `POST   /api/rawmaterials`                  - Create new material
- `PUT    /api/rawmaterials/{id}`             - Update material
- `DELETE /api/rawmaterials/{id}`             - Delete material

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