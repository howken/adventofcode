<?php

$file = fopen("input", "r") or die("Unable to open file!");
$input = array();

while(!feof($file)) {
    $input[] = trim(fgets($file));
}

fclose($file);

$input[] = "Disc #7 has 11 positions; at time=0, it is at position 0.";
$discs = [];

foreach($input as $row) {
    if (count($disc = explode(" ", $row)) > 1) {
        $disc[1] = substr($disc[1], 1);
        $disc[11] = substr($disc[11], 0, -1);
        $discs[] = $disc;
    }
}

$time = 0;

while (true) {
    $success = true;
    foreach($discs as $disc) {
        if ( (($time + (int) $disc[11] + (int) $disc[1]) % (int) $disc[3]) != 0 ) {
            $success = false;
            break;
        }
    }
    if ($success) {
        echo "time: $time\n";
        exit;
    }
    $time++;
}
    


?>
