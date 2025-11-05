import os

def combine_files(source_dir, file_names, output_file):
    """
    Combine multiple Java files from source_dir into a single output_file.

    :param source_dir: Path to directory containing source files.
    :param file_names: List of filenames (without .java extension). 
                       If empty, all .java files in the directory are included.
    :param output_file: Output file path (can include folder or just filename).
    """

    # If no files specified, grab all .java files in the directory
    if not file_names:
        file_names = [f[:-5] for f in os.listdir(source_dir) if f.endswith(".java")]
        print(f"📁 No specific files listed — found {len(file_names)} Java files in '{source_dir}'")

    with open(output_file, 'w', encoding='utf-8') as outfile:
        for name in file_names:
            file_name = name if name.endswith(".java") else f"{name}.java"
            file_path = os.path.join(source_dir, file_name)

            if os.path.exists(file_path):
                with open(file_path, 'r', encoding='utf-8') as infile:
                    content = infile.read()
                    outfile.write(f"\n// = File: {file_name} =\n")
                    outfile.write(content)
                    outfile.write("\n\n")
                print(f"✅ Added: {file_name}")
            else:
                print(f"⚠️ Skipped: {file_name} (not found)")

    print(f"\n🎉 All done! Combined file saved as: {output_file}")


if __name__ == "__main__":
    # 🔧 MODIFY THESE VALUES AS NEEDED
    source_directory = "dto"  # folder containing your Java files

    files_to_combine = [
    "Paymentprocessingarrangementmodality",
    "Contactpoint",
    "Arrangement",
    "Arrangementstatus",
    "Action",
    "Directdebitmandate",
    "Billpaymandate",
    "Billpaymandatefacilityarrangement",
    "Paymentarrangement",
    "Directdebitmandatearrangement"
]

    output_filename = "extract-payment-dtos.txt"

    combine_files(source_directory, files_to_combine, output_filename)
