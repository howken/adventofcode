<?php

$file = fopen("input", "r") or die("Unable to open file!");
$input = array();

while(!feof($file)) {
    $input[] = trim(fgets($file));
}

fclose($file);

$IPs = [];

foreach($input as $row) {
    $IPs[] = explode("-", $row);
}

array_pop($IPs);

usort($IPs, function ($a, $b) {
    $res = (int) $a[0] - (int) $b[0];
    return $res;
});

for($i = 1 ; $i < count($IPs) ; $i++) {
    if ( ( (int) $IPs[$i-1][1] + 1) < (int) $IPs[$i][0]) {
        echo "lowest IP: ".($IPs[$i-1][1] + 1)."\n";
        exit;
    }
}

?>
