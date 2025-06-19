#!/bin/zsh

# Check if a problem name and category are provided
if [ -z "$1" ] || [ -z "$2" ]; then
    echo "Please provide both a problem name and category number"
    echo "Usage: ./create_problem.sh ProblemName CategoryNum"
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
    exit 1
fi

# Map category number to folder name
case "$2" in
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
    *) echo "Invalid category number"; exit 1 ;;
esac

# Convert the problem name to proper case for C# and Java
# This will properly capitalize each word (e.g., "two sum" -> "TwoSum")
PROBLEM_NAME=$(echo "$1" | sed 's/\<./\U&/g' | sed 's/ //g')

# Create snake_case for Python
PROBLEM_NAME_PYTHON=$(echo "$1" | tr '[:upper:]' '[:lower:]' | tr ' ' '_')

# Create the problem directory in the appropriate category
BASE_DIR="$(dirname "$0")"
mkdir -p "$BASE_DIR/$CATEGORY/$PROBLEM_NAME"
PROBLEM_DIR="$BASE_DIR/$CATEGORY/$PROBLEM_NAME"

# Create C# file
cat > "$PROBLEM_DIR/$PROBLEM_NAME.cs" << EOL
using System;
using System.Collections.Generic;

public class $PROBLEM_NAME
{
    public static void Solution()
    {
        // TODO: Implement your solution here
        Console.WriteLine("$PROBLEM_NAME solution in C#");
    }
    
    public static void Main(string[] args)
    {
        Solution();
    }
}
EOL

# Create C# project file
cat > "$PROBLEM_DIR/$PROBLEM_NAME.csproj" << EOL
<Project Sdk="Microsoft.NET.Sdk">

  <PropertyGroup>
    <OutputType>Exe</OutputType>
    <TargetFramework>net6.0</TargetFramework>
    <ImplicitUsings>enable</ImplicitUsings>
    <Nullable>enable</Nullable>
  </PropertyGroup>

</Project>
EOL

# Create Java file
cat > "$PROBLEM_DIR/$PROBLEM_NAME.java" << EOL
public class $PROBLEM_NAME {
    public static void solution() {
        // TODO: Implement your solution here
        System.out.println("$PROBLEM_NAME solution in Java");
    }
    
    public static void main(String[] args) {
        solution();
    }
}
EOL

# Create Python file
cat > "$PROBLEM_DIR/${PROBLEM_NAME_PYTHON}.py" << EOL
def solution():
    # TODO: Implement your solution here
    print("$PROBLEM_NAME solution in Python")

if __name__ == "__main__":
    solution()
EOL

echo "Problem $PROBLEM_NAME created successfully!"
echo "Files created:"
echo "  - $PROBLEM_DIR/$PROBLEM_NAME.cs"
echo "  - $PROBLEM_DIR/$PROBLEM_NAME.csproj"
echo "  - $PROBLEM_DIR/$PROBLEM_NAME.java"
echo "  - $PROBLEM_DIR/${PROBLEM_NAME_PYTHON}.py"
