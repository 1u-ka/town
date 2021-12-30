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
  function invalid(object $defs) : string
  {
    return json_encode($this->invalidate($defs));
  }

  /** */
  private function invalidate(object $defs) : array
  {

    if ($this->missing($defs)) {
      return $this->missing($defs);
    }

    foreach ($defs as $k => $v) {
      if (gettype($v) !== $this->params->find($k)) {
        $incorrect[] = [ $k => gettype($v) ];
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
