import random
import time
import requests

url = "http://localhost:8081/customerArrived"

while True:
    random_age = random.randint(10, 60)
    print("Random age of customer:", random_age)

    payload = {"type": "customerArrived", "age": random_age, "timestamp": int(time.time() * 1000)}

    try:
        print(f"Sent {payload} to {url}")
        requests.post(url, json=payload, timeout=0.5)
    except requests.exceptions.RequestException as e:
        print(f"Error or timeout occurred: {e}")

    # Wait for 1 second
    time.sleep(0.1)
