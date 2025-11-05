import re
import os

def fix_md_linebreaks(input_file, output_file):
    with open(input_file, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    keywords = ['**Request**:', '**Method**:', '**Endpoint**:', '**Target URL**:', '**Description**:']
    fixed_lines = []

    for line in lines:
        stripped = line.rstrip('\n')
        # Check if line starts with one of the keywords
        if any(stripped.strip().startswith(kw) for kw in keywords):
            # Ensure two spaces at the end (before newline)
            if not stripped.endswith('  '):
                stripped += '  '
            fixed_lines.append(stripped + '\n')
        else:
            fixed_lines.append(line)

    with open(output_file, 'w', encoding='utf-8') as f:
        f.writelines(fixed_lines)

    print(f"Formatting fixed and saved to {output_file}")

# Change the filename here if needed
input_filename = "SOURCE.md"
output_filename = "TARGET.md"

# Run the function
if os.path.exists(input_filename):
    fix_md_linebreaks(input_filename, output_filename)
else:
    print(f"File {input_filename} not found.")
