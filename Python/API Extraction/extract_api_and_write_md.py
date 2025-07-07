import json
from collections import defaultdict
import os

# Path to your swagger.json
swagger_file_path = r"FILE_PATH\swagger.json"
output_file = "api_summary.md"

# Load the Swagger JSON file
if not os.path.exists(swagger_file_path):
    print(f"File not found: {swagger_file_path}")
    exit(1)

with open(swagger_file_path, 'r', encoding='utf-8') as f:
    spec = json.load(f)

# Group endpoints by tags
grouped = defaultdict(list)

for path, methods in spec.get('paths', {}).items():
    for method, details in methods.items():
        summary = details.get('summary') or details.get('operationId') or 'No description'
        tags = details.get('tags', ['General'])  # Default tag if not specified

        for tag in tags:
            grouped[tag].append({
                'method': method.upper(),
                'path': path,
                'summary': summary
            })

# Write to Markdown file
with open(output_file, 'w', encoding='utf-8') as md_file:
    md_file.write("# API Summary\n\n")

    for tag, endpoints in grouped.items():
        md_file.write(f"## {tag}\n\n")
        for ep in endpoints:
            md_file.write(f"- `{ep['method']} {ep['path']}` - {ep['summary']}\n")
        md_file.write("\n")

print(f"API summary has been written to '{output_file}'")
