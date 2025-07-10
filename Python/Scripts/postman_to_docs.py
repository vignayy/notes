import json
import os

# Your Postman collection file path
collection_path = r"FILE_PATH"
output_path = "API_Collection_Doc.txt"

def extract_items(items, docs, folder_path=""):
    for item in items:
        # Subfolders or requests
        if 'item' in item:
            # It's either a folder or has nested structure
            new_path = f"{folder_path} > {item['name']}" if folder_path else item['name']
            extract_items(item['item'], docs, new_path)
        elif 'request' in item:
            # It's an actual request
            name = item.get('name', 'Unnamed Request')
            method = item['request'].get('method', 'GET')
            url_data = item['request'].get('url', {})

            # Build full URL
            if isinstance(url_data, dict):
                if 'raw' in url_data:
                    url = url_data['raw']
                else:
                    url = build_url_from_parts(url_data)
            else:
                url = str(url_data)

            # Request Body
            request_body = ""
            if 'body' in item['request']:
                body_content = item['request']['body']
                if body_content and 'raw' in body_content:
                    request_body = body_content['raw'].strip()

            # First saved response (if any)
            response_body = ""
            if 'response' in item and len(item['response']) > 0:
                first_response = item['response'][0]
                response_body = first_response.get('body', '').strip()

            # Add section header only if folder_path exists
            if folder_path:
                docs.append(f"### {folder_path}\n")

            # Add request details
            docs.append(f"Request: {name}")
            docs.append(f"Method: {method}")
            docs.append(f"Endpoint: {url}")

            if request_body:
                 docs.append("\nRequest Body:\n" + request_body)

            if response_body:
                docs.append("\nResponse Body:\n" + response_body)

            docs.append("\n" + "-"*60 + "\n")

def build_url_from_parts(url_data):
    host = ".".join(url_data.get("host", []))
    path = "/".join(url_data.get("path", []))
    query = url_data.get("query", [])
    query_str = ""
    if query:
        query_str = "?" + "&".join(f"{q.get('key')}={q.get('value')}" for q in query if 'key' in q and 'value' in q)
    return f"https://{host}/{path}{query_str}"

def main():
    with open(collection_path, 'r', encoding='utf-8') as f:
        collection = json.load(f)

    docs = []
    items = collection.get('item', [])
    extract_items(items, docs)

    with open(output_path, 'w', encoding='utf-8') as f:
        f.write("\n".join(docs))

    print(f"\nAPI documentation generated: {os.path.abspath(output_path)}")

if __name__ == "__main__":
    main()
