ion about the OS distribution that is compatible
        with Python's :func:`platform.linux_distribution`, supporting a subset
        of its parameters.

        For details, see :func:`distro.linux_distribution`.
        """
        return (
            self.name() if full_distribution_name else self.id(),
            self.version(),
            self._os_release_info.get("release_codename") or self.codename(),
        )

    def id(self) -> str:
        """Return the distro ID of the OS distribution, as a string.

        For details, see :func:`distro.id`.
        """

        def normalize(distro_id: str, table: Dict[str, str]) -> str:
            distro_id = distro_id.lower().replace(" ", "_")
            return t