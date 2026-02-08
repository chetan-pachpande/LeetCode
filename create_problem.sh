#!/bin/zsh

set -euo pipefail

usage() {
  echo "Usage (recommended): ./create_problem.sh <leetcode_number> \"<problem_name>\" <category_number>"
  echo "Example: ./create_problem.sh 242 \"Valid Anagram\" 1"
  echo ""
  echo "Legacy usage (still supported): ./create_problem.sh \"<problem_name>\" <category_number>"
  echo ""
  echo "Categories:"
  echo "1: Arrays & Hashing"
  echo "2: Two Pointers"
  echo "3: Sliding Window"
  echo "4: Stack"
  echo "5: Binary Search"
  echo "6: Linked List"
  echo "7: Trees"
  echo "8: Tries"
  echo "9: Heap / Priority Queue"
  echo "10: Backtracking"
  echo "11: Graphs"
  echo "12: Advanced Graphs"
  echo "13: 1D Dynamic Programming"
  echo "14: 2D Dynamic Programming"
  echo "15: Greedy"
  echo "16: Intervals"
  echo "17: Math & Geometry"
  echo "18: Bit Manipulation"
}

if [ "$#" -lt 2 ] || [ "$#" -gt 3 ]; then
  usage
  exit 1
fi

LC_NUMBER=""
PROBLEM_INPUT=""
CATEGORY_NUM=""

if [ "$#" -eq 3 ]; then
  LC_NUMBER="$1"
  PROBLEM_INPUT="$2"
  CATEGORY_NUM="$3"
else
  PROBLEM_INPUT="$1"
  CATEGORY_NUM="$2"
fi

case "$CATEGORY_NUM" in
  1) CATEGORY="01_Arrays_Hashing" ;;
  2) CATEGORY="02_Two_Pointers" ;;
  3) CATEGORY="03_Sliding_Window" ;;
  4) CATEGORY="04_Stack" ;;
  5) CATEGORY="05_Binary_Search" ;;
  6) CATEGORY="06_Linked_List" ;;
  7) CATEGORY="07_Trees" ;;
  8) CATEGORY="08_Tries" ;;
  9) CATEGORY="09_Heap_Priority_Queue" ;;
  10) CATEGORY="10_Backtracking" ;;
  11) CATEGORY="11_Graphs" ;;
  12) CATEGORY="12_Advanced_Graphs" ;;
  13) CATEGORY="13_1D_Dynamic_Programming" ;;
  14) CATEGORY="14_2D_Dynamic_Programming" ;;
  15) CATEGORY="15_Greedy" ;;
  16) CATEGORY="16_Intervals" ;;
  17) CATEGORY="17_Math_Geometry" ;;
  18) CATEGORY="18_Bit_Manipulation" ;;
  *) echo "Invalid category number: $CATEGORY_NUM"; usage; exit 1 ;;
esac

# Normalize to PascalCase for folder/class names.
PROBLEM_NAME=$(echo "$PROBLEM_INPUT" | sed 's/[^[:alnum:] ]//g' | sed 's/\<./\U&/g' | tr -d ' ')
if [ -z "$PROBLEM_NAME" ]; then
  echo "Problem name cannot be empty after normalization."
  exit 1
fi

if [ -n "$LC_NUMBER" ]; then
  if ! echo "$LC_NUMBER" | grep -Eq '^[0-9]+$'; then
    echo "LeetCode number must be numeric."
    exit 1
  fi
  LC_PADDED=$(printf "%03d" "$LC_NUMBER")
  FOLDER_NAME="LC_${LC_PADDED}_${PROBLEM_NAME}"
else
  FOLDER_NAME="LC_XXX_${PROBLEM_NAME}"
fi

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
PROBLEM_DIR="$BASE_DIR/$CATEGORY/Problems/$FOLDER_NAME"

if [ -d "$PROBLEM_DIR" ]; then
  echo "Problem already exists: $PROBLEM_DIR"
  exit 1
fi

mkdir -p "$PROBLEM_DIR"

cat > "$PROBLEM_DIR/${PROBLEM_NAME}.java" <<EOL
public class ${PROBLEM_NAME} {
    public static void main(String[] args) {
        // TODO: Implement solution
    }
}
EOL

cat > "$PROBLEM_DIR/Solution_Steps.txt" <<EOL
1) Understand constraints and edge cases.
2) Choose the optimal approach.
3) Implement and test with custom cases.
EOL

cat > "$PROBLEM_DIR/README.md" <<EOL
# ${FOLDER_NAME}

- Problem: ${PROBLEM_INPUT}
- Topic: ${CATEGORY}

## Files
- ${PROBLEM_NAME}.java
- Solution_Steps.txt
EOL

"$BASE_DIR/scripts/update_problem_indexes.sh" >/dev/null

echo "Problem created: $PROBLEM_DIR"
echo "Created files:"
echo "  - $PROBLEM_DIR/${PROBLEM_NAME}.java"
echo "  - $PROBLEM_DIR/Solution_Steps.txt"
echo "  - $PROBLEM_DIR/README.md"
