import sys
import textwrap
from optparse import Values
from typing import List

from pip._internal.cli.base_command import Command
from pip._internal.cli.status_codes import SUCCESS
from pip._internal.utils.misc import get_prog

BASE_COMPLETION = """
# pip {shell} completion start{script}# pip {shell} completion end
"""

COMPLETION_SCRIPTS = {
    "bash": """
        _pip_completion()
        {{
            COMPREPLY=( $( COMP_WORDS="${{COMP_WORDS[*]}}" \\
                           COMP_CWORD=$COMP_CWORD \\
                           PIP_AUTO_COMPLETE=1 $1 2>/dev/null ) )
        }}
        complete -o default -F _pip_completion {prog}
    """,
    "zsh": """
        #compdef -P pip[0-9.]#
        __pip() {{
          compadd 