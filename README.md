# 2268proj

### Key Components
- **Python Script**: [`/customer_arrived.py`](customer_arrived.py)
- **Siddhi Code**: [`/HostStationAtRestaurant.siddhi`](HostStationAtRestaurant.siddhi)
- **BPMN Diagram**: [`/src/main/resources/sellFood.bpmn`](src/main/resources/sellFood.bpmn)
- **DMN Diagram**: [`/src/main/resources/discountDecision.dmn`](src/main/resources/discountDecision.dmn)

---

## Prerequisites To Run the Spring server (Camunda)
Ensure you have the following installed:
- **Java 17**
- **Maven 3**

---

## How to Run the Project

1. **Clone the Repository**
   ```bash
   git clone https://github.com/pavliuc75/2268proj.git
   cd 2268proj
    ```
2. **Run the Spring Server**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. **Access the Camunda Webapp**
    - Go to [http://localhost:8080/](http://localhost:8080/) in your browser.
    - Log in with the credentials `demo` and `demo`.


## Making a New Process Instance

To create a new process instance (without Siddhi), use the following `curl` command:

```bash
curl -X POST http://localhost:8080/restaurant/customer-arrived \
-H "Content-Type: application/json" \
-d '{
    "type": "customerArrived",
    "age": 30
}'