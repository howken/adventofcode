<?php

$input = 0;

$elfs = array_fill(0, $input, "x");
end($elfs);
$last_key = key($elfs);
reset($elfs);

while(count($elfs) > 1) {
    $key = key($elfs);
    if ($key == $last_key) {
        reset($elfs);
        $key = key($elfs);
        next($elfs);
        unset($elfs[$key]);
    } else {
        next($elfs);
        $key = key($elfs);
        if ($key == $last_key) {
            prev($elfs);
            $last_key = key($elfs);
            reset($elfs);
            unset($elfs[$key]);
        } else {
            unset($elfs[$key]);
        }
    }
}

echo "last elf: ".(key($elfs) + 1)."\n";

?>
