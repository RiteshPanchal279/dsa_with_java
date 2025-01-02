"""Cache Management
"""

import hashlib
import json
import logging
import os
from pathlib import Path
from typing import Any, Dict, List, Optional

from pip._vendor.packaging.tags import Tag, interpreter_name, interpreter_version
from pip._vendor.packaging.utils import canonicalize_name

from pip._internal.exceptions import InvalidWheelFilename
from pip._internal.models.direct_url import DirectUrl
from pip._internal.models.link import Link
from pip._internal.models.wheel import Wheel
from pip._internal.utils.temp_dir import TempDirectory, tempdir_kinds
from pip._internal.utils.urls import path_to_url

logger = logging.getLogger(__name__)

ORIGIN_JSON_NAME = "origin.json"


def _hash_dict(d: Dict[str, str]) -> str:
    """Return a stable sha224 of a dictionary."""
    s = json.dumps(d, sort_keys=True, separators=(",", ":"), ensure_ascii=True)
    return hashlib.sha224(s.encode("ascii")).hexdigest()


class Cache:
    """An abstract class - provides cache directories for data from links

    :param cache_dir: The root of the cache.
    """

    def __init__(self, cache_dir: str) -> None:
        super().__init__()
        