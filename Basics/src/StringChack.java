"""PEP 656 support.

This module implements logic to detect if the currently running Python is
linked against musl, and what musl version is used.
"""

from __future__ import annotations

import functools
import re
import subprocess
import sys
from typing import Iterator, NamedTuple, Sequence

from ._elffile import ELFFile


class _MuslVersion(NamedTuple):
    major: int
    minor: int


def _parse_musl_version(output: str) -> _MuslVersion | None:
    lines = [n for n in (n.strip() for n in output.splitlines()) if n]
    if len(lines) < 2 or lines[0][:4] != "musl":
        return None
    m = re.match(r"Version (\d+)\.(\d+)", lines[1])
    if not m:
        return None
    return _MuslVersion(major=int(m.group(1)), minor=int(m.group(2)))


@functools.lru_cache
def _get_musl_version(executable: str) -> _MuslVersion | None:
    """Detect currently-running musl runtime version.

    This is done by checking the specified executable's d