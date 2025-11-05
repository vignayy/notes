import os
import re

def create_java_files_from_md(md_file_path="gemini_response.md"):
    """
    Reads a markdown file containing Java enum definitions and creates
    the corresponding .java files in the correct package structure.
    """
    # Define the base directory for the output
    base_output_dir = "generated-enums"
    package_path = "enums"
    full_output_path = os.path.join(base_output_dir, package_path)

    # 1. Create the directory structure if it doesn't exist
    try:
        os.makedirs(full_output_path, exist_ok=True)
        print(f"✅ Successfully created directory structure: {full_output_path}")
    except OSError as e:
        print(f"❌ Error creating directories: {e}")
        return

    # 2. Read the markdown file content
    try:
        with open(md_file_path, 'r', encoding='utf-8') as f:
            content = f.read()
    except FileNotFoundError:
        print(f"❌ Error: The file '{md_file_path}' was not found.")
        print("Please make sure it's in the same directory as this script.")
        return

    # 3. Split the content into sections for each file
    file_chunks = content.split('---')

    file_count = 0
    # 4. Process each chunk to extract filename and code
    for chunk in file_chunks:
        if not chunk.strip():
            continue

        filename_match = re.search(r'###\s*(.*\.java)', chunk)
        code_match = re.search(r'```java\n(.*?)\n```', chunk, re.DOTALL)

        if filename_match and code_match:
            # --- THIS IS THE FIXED LINE ---
            filename = filename_match.group(1).strip().strip('`')
            # -----------------------------
            
            java_code = code_match.group(1).strip()
            
            output_file_path = os.path.join(full_output_path, filename)

            # 5. Write the extracted code to the .java file
            try:
                with open(output_file_path, 'w', encoding='utf-8') as f:
                    f.write(java_code)
                print(f"   -> Created file: {filename}")
                file_count += 1
            except IOError as e:
                print(f"   -> ❌ Error writing file {filename}: {e}")

    print(f"\n🎉 Success! A total of {file_count} Java enum files have been generated.")
    print(f"You can find them in the '{base_output_dir}/{package_path}' folder.")


if __name__ == "__main__":
    create_java_files_from_md()