<?php

namespace iors_usd;

use Phel\Interop\PhelCallerTrait;

class Router {

  /** */
  use PhelCallerTrait;

  /** */
  public function register()
  {
    add_action(
      "rest_api_init",
      function () {

        /** */
        register_rest_route(
          "wp/v2",
          "(?P<ctl>\w+)/(?P<act>\w+)/(?P<id>\d+)",
          [ "methods"  => "GET",
            "callback" => function(...$args) {
              return $this->callPhel(
                'iors-usd\\chain', 'execute', ...$args
              );
            }
          ]);

      });
  }

}
