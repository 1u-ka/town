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
    return $this->callPhel('iors-usd\chain',
                           'execute',
                           (object) $_SERVER);
  }
}

Phel::run(__DIR__, 'iors-usd\\chain');
print (new Main)->handle();
