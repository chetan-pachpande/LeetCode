#!/bin/zsh

set -euo pipefail
setopt NULL_GLOB

BASE_DIR="$(cd "$(dirname "$0")/.." && pwd)"

category_title() {
  local raw="$1"
  raw="${raw#??_}"
  echo "$raw" | tr '_' ' '
}

category_number() {
  local raw="$1"
  echo "$raw" | cut -c1-2
}

# Build root README as the canonical navigation file.
root_readme="$BASE_DIR/README.md"
{
  echo "# LeetCode Workspace"
  echo ""
  echo "This repository is organized by topic."
  echo "Each topic folder contains:"
  echo "- \`BeginnerAlgorithms/\`"
  echo "- \`AdvancedAlgorithms/\`"
  echo "- \`Problems/\`"
  echo ""
  echo "## Topics"
  echo ""

  total=0
  for category in "$BASE_DIR"/??_*; do
    [ -d "$category" ] || continue
    [ -d "$category/Problems" ] || continue

    category_name="$(basename "$category")"
    title="$(category_title "$category_name")"
    count=$(find "$category/Problems" -mindepth 1 -maxdepth 1 -type d -name 'LC_*' | wc -l | tr -d ' ')
    total=$((total + count))

    echo "- [${title}](./${category_name}/Problems/) (${count})"
  done

  echo ""
  echo "## Totals"
  echo ""
  echo "- Solved problem folders: ${total}"
  echo ""
  echo "## Commands"
  echo ""
  echo "- Create a problem: \`./create_problem.sh <leetcode_number> \"<problem_name>\" <category_number>\`"
  echo "- Refresh indexes: \`./scripts/update_problem_indexes.sh\`"
} > "$root_readme"

# Build per-category indexes.
for category in "$BASE_DIR"/??_*; do
  [ -d "$category" ] || continue
  [ -d "$category/Problems" ] || continue

  category_name="$(basename "$category")"
  title="$(category_title "$category_name")"
  number="$(category_number "$category_name")"
  number_int=$((10#$number))
  topic_readme="$category/README.md"
  problems_dir="$category/Problems"
  problems_readme="$problems_dir/README.md"
  problem_count=$(find "$problems_dir" -mindepth 1 -maxdepth 1 -type d -name 'LC_*' | wc -l | tr -d ' ')

  {
    echo "# ${title}"
    echo ""
    echo "Auto-generated overview for this topic."
    echo ""
    echo "## Structure"
    echo ""
    echo "- [Beginner Algorithms](./BeginnerAlgorithms/)"
    echo "- [Advanced Algorithms](./AdvancedAlgorithms/)"
    echo "- [Problems](./Problems/)"
    echo ""
    echo "## Current Progress"
    echo ""
    echo "- Solved problem folders: ${problem_count}"
    echo ""
    echo "## Commands"
    echo ""
    echo "- Create a problem here: \`./create_problem.sh <leetcode_number> \"<problem_name>\" ${number_int}\`"
    echo "- Refresh indexes: \`./scripts/update_problem_indexes.sh\`"
  } > "$topic_readme"

  {
    echo "# Problems - ${title}"
    echo ""
    echo "Auto-generated index of problems in this topic."
    echo ""
    echo "## Problem List"
    echo ""

    i=1
    found=0
    for p in "$problems_dir"/LC_*; do
      [ -d "$p" ] || continue
      found=1
      name="$(basename "$p")"
      echo "${i}. [${name}](./${name})"
      i=$((i + 1))
    done

    if [ "$found" -eq 0 ]; then
      echo "1. No problems added yet"
    fi
  } > "$problems_readme"
done

echo "Indexes updated successfully."
