<?php
function yuesefu($n,$m) {
    $r=0;
    for($i=2; $i<=$n; $i++) {
        $r=($r+$m)%$i;
    }
    return $r+1;
}
echo yuesefu(10,3)."是猴王";