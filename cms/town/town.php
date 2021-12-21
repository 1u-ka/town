<?php
/**
 * Plugin Name: Town
 */
require __DIR__ . '/vendor/autoload.php';

use iors_usd\Router;

use Phel\Phel;

Phel::run(__DIR__, 'iors-usd\\chain');

add_action(
  'plugins_loaded',
  fn() => (new Router)->register()
);
