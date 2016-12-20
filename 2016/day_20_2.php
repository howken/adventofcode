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
array_pop($IPs); // remove last input as there is no data there.

usort($IPs, function ($a, $b) {
    if ($a[0] == $b[0]) {
        $res = (int) $a[1] - (int) $b[1];
    } else {
        $res = (int) $a[0] - (int) $b[0];
    }
    return $res;
});

$count = 0;
$newIPs = [];

$rs = (int) $IPs[0][0];
$re = (int) $IPs[0][1];

for($i = 1 ; $i < count($IPs) ; $i++) {
    $high = (int) $IPs[$i][1];
    $low = (int) $IPs[$i][0];
    // check overlapp
    if ($low > $re) { // no overlap, save range
        $newIPs[] = [$rs, $re];
        $rs = $low;
        $re = $high;
    } else {
        if ($high > $re) {
            $re = $high;
        }
    }
}
$newIPs[] = [$rs, $re];

for($i = 1 ; $i < count($newIPs) ; $i++) {
    $low = ((int) $newIPs[$i-1][1]);
    $high = (int) $newIPs[$i][0];

    if ( $low < $high) {
        $count = $count + ($high - ($low+1));
    }
}

$count = $count + (4294967295 - $newIPs[count($newIPs)-1][1]);

echo "unblocked IPs: $count\n";

?>
