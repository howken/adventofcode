<?php

$file = fopen("input", "r") or die("Unable to open file!");
$input = array();

while(!feof($file)) {
    $input[] = trim(fgets($file));
}

fclose($file);

$discs = [];

foreach($input as $row) {
    if (count($disc = explode(" ", $row)) > 1) {
        $disc[1] = substr($disc[1], 1);
        $discs[$disc[1]] = $disc;
    }
}

$time = 0;

while (true) {
    $success = true;
    foreach($discs as $disc) {
        if ( ((($time + $disc[1]) % $disc[3]) + $disc[11]) != $disc[3] ) {
            $success = false;
        }
    }
    if ($success) {
        echo "time: $time\n";
        exit;
    }
    $time++;
}
    


?>
