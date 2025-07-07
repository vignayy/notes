import json
from collections import defaultdict
import os

# Load the Swagger file
swagger_file_path = r"FILE_PATH\swagger.json"

if not os.path.exists(swagger_file_path):
    print(f"File not found: {swagger_file_path}")
    exit(1)

with open(swagger_file_path, 'r', encoding='utf-8') as f:
    spec = json.load(f)

# Group endpoints by tag
grouped = defaultdict(list)

for path, methods in spec.get('paths', {}).items():
    for method, details in methods.items():
        summary = details.get('summary') or details.get('operationId') or 'No description'
        tags = details.get('tags', ['General'])  # default tag if none present

        for tag in tags:
            grouped[tag].append({
                'method': method.upper(),
                'path': path,
                'summary': summary
            })

# Output to Markdown-style console printout
for tag, endpoints in grouped.items():
    print(f"\n## {tag}")
    for ep in endpoints:
        print(f"- `{ep['method']} {ep['path']}` - {ep['summary']}")
