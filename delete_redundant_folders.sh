#!/bin/zsh
# Script to delete the redundant folders outside of the LeetCode directory
# Created: June 18, 2025

# Get parent directory of LeetCode
PARENT_DIR=$(dirname "$(cd "$(dirname "$0")" && pwd)")

# List of folders to delete
FOLDERS=(
    "$PARENT_DIR/01. Arrays & Hashing"
    "$PARENT_DIR/02. Two Pointers"
    "$PARENT_DIR/03. Sliding Window"
    "$PARENT_DIR/04. Stack"
    "$PARENT_DIR/05. Binary Search"
    "$PARENT_DIR/06. Linked List"
    "$PARENT_DIR/07. Trees"
    "$PARENT_DIR/08. Tries"
    "$PARENT_DIR/09. Heap / Priority Queue"
    "$PARENT_DIR/10. Backtracking"
    "$PARENT_DIR/11. Graphs"
    "$PARENT_DIR/12. Advanced Graphs"
    "$PARENT_DIR/13. 1D Dynamic Programming"
    "$PARENT_DIR/14. 2D Dynamic Programming"
    "$PARENT_DIR/15. Greedy"
    "$PARENT_DIR/16. Intervals"
    "$PARENT_DIR/17. Math & Geometry"
    "$PARENT_DIR/18. Bit Manipulation"
)

echo "This script will delete the following folders:"
for folder in "${FOLDERS[@]}"; do
    echo "  - $folder"
done

echo ""
echo "WARNING: This operation cannot be undone!"
echo "Please ensure you have backed up any important content"
echo ""

echo -n "Are you sure you want to proceed? (yes/no): "
read CONFIRM

if [ "$CONFIRM" = "yes" ]; then
    echo "Deleting folders..."
    for folder in "${FOLDERS[@]}"; do
        if [ -d "$folder" ]; then
            echo "Deleting $folder"
            rm -rf "$folder"
        else
            echo "Folder not found: $folder"
        fi
    done
    echo "Deletion complete!"
else
    echo "Operation cancelled"
fi
