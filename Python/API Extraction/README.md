# API Extraction from Swagger/OpenAPI JSON

This repository contains Python scripts to extract and group API endpoints from your Swagger/OpenAPI JSON specification, typically served by SpringDoc OpenAPI.

---

## Swagger/OpenAPI JSON Spec Endpoint

Based on your SpringDoc configuration, your API docs are served at:

```
http://localhost:8080/v1/entitlement/v3/api-docs
```

The Swagger UI is available at:

```
http://localhost:8080/swagger-ui.html
```

---

## How to Extract API Endpoints

### Step 1: Download the Swagger JSON file

Use your browser or `curl` to download the full API specification as JSON:

```bash
curl http://localhost:8080/v1/entitlement/v3/api-docs -o swagger.json
```

---

### Step 2: Use the Python extraction scripts

This repo contains two Python scripts to help extract and display the API endpoints:

* **`extract_api_and_print.py`**
  Prints the API endpoints grouped by tags directly to the console.

* **`extract_api_and_write_md.py`**
  Writes the grouped API endpoints to a Markdown (`.md`) file for easier documentation.

---

### Step 3: Update file path in scripts

Before running, open the Python script(s) and update the variable `swagger_file_path` to point to your downloaded `swagger.json` file, e.g.:

```python
swagger_file_path = r"./swagger.json"
```

---

### Step 4: Run the script(s)

Run the script using Python:

```bash
python extract_api_and_print.py
```

or

```bash
python extract_api_and_write_md.py
```

---

## Troubleshooting

* Ensure your server is running and the API docs endpoint is accessible.
* Check for any CORS or authentication filters that might block access to the Swagger JSON.
* Enable logging or debug your SpringDoc configuration if the endpoint is not reachable.

---

## Summary

* Download Swagger JSON from the configured endpoint.
* Use `extract_api_and_print.py` or `extract_api_and_write_md.py` to parse and display/write your API endpoints.
* Scripts expect a valid `swagger.json` file — update the path accordingly.

---
