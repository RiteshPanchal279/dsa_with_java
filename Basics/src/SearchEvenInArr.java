en consult that directory when looking up
        cache hits.

        We only insert things into the cache if they have plausible version
        numbers, so that we don't contaminate the cache with things that were
        not unique. E.g. ./package might have dozens of installs done for it
        and build a version of 0.0...and if we built and cached a wheel, we'd
        end up using the same wheel even if the source has been edited.

        :param link: The link of the sdist for which this will cache wheels.
        """
        parts = self._get_cache_path_parts(link)
        assert self.cache_dir
        # Store wheels within the root cache_dir
        return os.path.join(self.cache_dir, "wheels", *parts)

    def get(
   