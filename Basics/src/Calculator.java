tp_files=num_http_files,
                wheels_cache_location=wheels_cache_location,
                package_count=num_packages,
                wheels_cache_size=wheels_cache_size,
            )
            .strip()
        )

        logger.info(message)

    def list_cache_items(self, options: Values, args: List[Any]) -> None:
        if len(args) > 1:
            raise CommandError("Too many arguments")

        if args:
            pattern = args[0]
        else:
            pattern = "*"

        files = self._find_wheels(options, pattern)
        if options.list_format == "human":
            self.format_for_human(files)
        else:
            self.format_for_abspath(files)

    def format_for_human(self, files: List[str]) -> None:
        if not files:
            logger.info("No locally built wheels cached.")
            return

        results = []
        for filename in files:
            wheel = os.path.basename(filename)
            size = filesystem.format_file_size(filename)
            results.append(f" - {wheel} ({size})")
        logger.info("Cache contents:\n")
        logger.info("\n".join(sorted(results)))

    def format_for_abspath(self, files: List[str]) -> None:
        if files:
      