th(words[1])

        else:
            raise DistlibException('unknown action %r' % action)

        return action, patterns, thedir, dir_pattern

    def _include_pattern(self, pattern, anchor=True, prefix=None,
                         is_regex=False):
        """Select strings (presumably filenames) from 'self.files' that
        match 'pattern', a Unix-style wildcard (glob) pattern.

        Patterns are not quite the same as implemented by the 'fnmatch'
        module: '*' and '?'  match non-special characters, where "special"
        is platform-dependent: slash on Unix; colon, slash, and backslash on
        DOS/Windows; and colon on Mac OS.

        If 'anchor' is true (the default), then the pattern match is more
        stringent: "*.py" will match "foo.py" but not "foo/bar.py".  If
        'anchor' is false, both of these will match.

        If 'prefix' is supplied, then only filenames starting with 'prefix'
        (itself a pattern) and ending with 'pattern', with anything in between
        them, will match.  'anchor' is ignored in this case.

        If 'is_regex' is true, 'anchor' and 'prefix' are ignored, and
        'pattern' is assumed to be either a string containing a regex or a
        regex object -- no translation is done, the regex is just compiled
        and used as-is.

        Selected strings will be added to self.files.

        Return True if files are found.
        """
        # XXX docstring lying about what the special chars are?
        found = False
        pattern_re = self._translate_pattern(pattern, anchor, prefix, is_regex)
