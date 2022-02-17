<?php

namespace iors_usd\lib\struct;

use Phel\Lang\Collections\Map\PersistentArrayMap;

use Exception;

/**
 * @todo   can likely be rewritten in phel
 */
class Validator {

  private $params;

  function __construct(PersistentArrayMap $params)
  {
    $this->params = $params;
  }

  /** */
  function invalid(object $defs) : ?string
  {
    if (! $invalid = $this->invalidate($defs)) {
      return null;
    }

    return json_encode($invalid);
  }

  /** */
  private function invalidate(object $defs) : array
  {
    $incorrect = [];

    if ($this->missing($defs)) {
      return $this->missing($defs);
    }

    foreach ($defs as $k => $v) {
      if (gettype($v) !== $this->params->find($k)) {
        $incorrect[] = [ $k => sprintf('!%s', $this->params->find($k)) ];
      }
    }

    return $incorrect;
  }

  /** */
  private function missing(object $defs) : array
  {
    $missing = [];

    if ($this->params->count() == count($defs)) {
      return $missing;
    }

    foreach ($this->params as $k => $v) {
      if (! isset($defs->$k)) {
        $missing[] = [ $k => null ];
      }
    }

    return $missing;
  }

}
