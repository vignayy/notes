import os
import shutil

# Source folder (where to search)
source = os.path.expanduser("~/Downloads/FinX")

# Destination folder = the folder where you run the script
destination = os.getcwd()

count = 0
for root, dirs, files in os.walk(source):
    for f in files:
        if f.endswith(".py"):
            src_file = os.path.join(root, f)
            dest_file = os.path.join(destination, f)

            # Copy file, overwriting if already exists
            shutil.copy2(src_file, dest_file)
            count += 1
            print(f"Copied: {src_file} → {dest_file}")

print(f"\n✅ Done! {count} Python files copied into: {destination}")
