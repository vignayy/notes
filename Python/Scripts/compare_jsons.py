import json
import os

def normalize_keys(obj):
    """Recursively converts all keys in a JSON object to lowercase."""
    if isinstance(obj, dict):
        return {k.lower(): normalize_keys(v) for k, v in obj.items()}
    if isinstance(obj, list):
        return [normalize_keys(elem) for elem in obj]
    return obj

def find_differences(generated_obj, bian_obj, path=""):
    """Recursively compares two JSON objects and returns a list of differences."""
    differences = []
    
    generated_keys = set(generated_obj.keys())
    bian_keys = set(bian_obj.keys())

    missing_keys = bian_keys - generated_keys
    for key in missing_keys:
        differences.append(f"Field Missing in Generated JSON: {path}.{key}")

    extra_keys = generated_keys - bian_keys
    for key in extra_keys:
        differences.append(f"Extra Field in Generated JSON: {path}.{key}")

    for key in generated_keys.intersection(bian_keys):
        new_path = f"{path}.{key}" if path else key
        
        val_gen = generated_obj[key]
        val_bian = bian_obj[key]

        if type(val_gen) != type(val_bian):
            diff_str = (
                f"Structural Mismatch at '{new_path}':\n"
                f"  - Generated Type: {type(val_gen).__name__}\n"
                f"  - BIAN Portal Type: {type(val_bian).__name__}"
            )
            differences.append(diff_str)
            continue

        if isinstance(val_gen, dict):
            differences.extend(find_differences(val_gen, val_bian, path=new_path))

    return differences

def compare_and_report(generated_file, bian_file, report_file):
    """Main function to load, compare, and report differences."""
    print(f"Comparing '{generated_file}' with '{bian_file}'...")
    
    try:
        # It's good practice to specify UTF-8 for reading JSONs too
        with open(generated_file, 'r', encoding='utf-8') as f:
            generated_json = json.load(f)
        with open(bian_file, 'r', encoding='utf-8') as f:
            bian_json = json.load(f)
    except (json.JSONDecodeError, FileNotFoundError) as e:
        print(f"Error reading JSON files: {e}")
        return

    norm_generated = normalize_keys(generated_json)
    norm_bian = normalize_keys(bian_json)
    diffs = find_differences(norm_generated, norm_bian)

    # Write the report using UTF-8 encoding to support all characters
    with open(report_file, 'w', encoding='utf-8') as f:
        if not diffs:
            report_message = "SUCCESS: The JSON structures are a match (ignoring case)."
            print(report_message)
            f.write(report_message + "\n")
        else:
            report_message = f"Found {len(diffs)} structural differences:"
            print(report_message)
            f.write(report_message + "\n\n")
            for i, d in enumerate(diffs, 1):
                f.write(f"{i}. {d}\n\n")
    
    print(f"Report saved to '{report_file}'")


if __name__ == "__main__":
    # --- CONFIGURATION ---
    # Your changes here were perfectly correct!
    generated_json_filename = "savings-generated.json"
    bian_portal_json_filename = "savings-bian.json"
    output_report_filename = "savings-differences-report.txt"
    # -------------------

    compare_and_report(generated_json_filename, bian_portal_json_filename, output_report_filename)