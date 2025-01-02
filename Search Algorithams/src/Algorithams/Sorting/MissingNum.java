return the relevant words.  'action' is always
        # defined: it's the first word of the line.  Which of the other
        # three are defined depends on the action; it'll be either
        # patterns, (dir and patterns), or (dirpattern).
        action, patterns, thedir, dirpattern = self._parse_directive(directive)

        # OK, now we know that the action is valid and we have the
        # right number of words on the line for that action -- so we
        # can proceed with minimal error-checking.
        if action == 'include':
            for pattern in patterns:
                if not self._include_pattern(pattern, anchor=True):
                    logger.warning('no files found matching %r', pattern)

        elif action == 'exclude':
            for pattern in patterns:
                self._exclude_pattern(pattern, anchor=True)

        elif action == 'global-include':
            for pattern in patterns:
                if not self._include_pattern(pattern, anchor=False):
                    logger.warning('no files found matching %r '
                                   'anywhere in distribution', pattern)

        elif action == 'global-exclude':
            for 