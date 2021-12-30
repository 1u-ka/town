<?php
require __DIR__ . '/vendor/autoload.php';

use iors_usd\Router;

use Phel\Interop\PhelCallerTrait;
use Phel\Phel;

class Main {

  /** */
  use PhelCallerTrait;

  /** */
  function __construct()
  {

  }

  /** */
  function handle()
  {
    $json    = fn ($v) => json_decode(json_encode($v, JSON_NUMERIC_CHECK));
    $q       = (object) $_SERVER;
    $q->POST = $json($_POST);
    $q->GET  = $json($_GET);

    return $this->callPhel('iors-usd\chain',
                           'execute',
                           $q);
  }
}

Phel::run(__DIR__, 'iors-usd\\chain');
print (new Main)->handle();
