<?php

error_reporting(E_ERROR | E_WARNING | E_PARSE);

$input = "--INPUT--";
$count = 0; // start with count of safe tiles in input
$n_rows = 0; // rows to check
$rows = [str_split($input)];

for ($row = 1 ; $row < $n_rows ; $row++) {
    $rows[$row] = [];
    for($cell = 0 ; $cell < count($rows[0]) ; $cell++) {
        if ($rows[$row-1][$cell-1] == "^" and $rows[$row-1][$cell] == "^" and $rows[$row-1][$cell+1] != "^") {
            $rows[$row][$cell] = "^";
        } else if ($rows[$row-1][$cell-1] != "^" and $rows[$row-1][$cell] == "^" and $rows[$row-1][$cell+1] == "^") {
            $rows[$row][$cell] = "^";
        } else if ($rows[$row-1][$cell-1] == "^" and $rows[$row-1][$cell] != "^" and $rows[$row-1][$cell+1] != "^") {
            $rows[$row][$cell] = "^";
        } else if ($rows[$row-1][$cell-1] != "^" and $rows[$row-1][$cell] != "^" and $rows[$row-1][$cell+1] == "^") {
            $rows[$row][$cell] = "^";
        } else {
            $rows[$row][$cell] = ".";
            $count++;
        }
    }
}

echo "safe tiles: $count\n";

?>
