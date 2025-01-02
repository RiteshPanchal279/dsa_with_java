ormatted_target = target_python.format_given()
    suffix = ""
    if formatted_target:
        suffix = f" (target: {formatted_target})"

    msg = f"Compatible tags: {len(tags)}{suffix}"
    logger.info(msg)

    if options.verbose < 1 and len(tags) > tag_limit:
        tags_limited = True
        tags = tags[:tag_limit]
    else:
        tags_limited = False

    with indent_log():
        for tag in tags:
            logger.info(str(tag))

        if tags_limited:
            msg = f"...\n[First {tag_limit} tags shown. Pass --verbose to show all.]"
            logger.info(msg)


def ca_bundle_info(config: Configuration) -> str:
    levels = {key.split(".", 1)[0] for key, _ in config.items()}
    if not levels:
        return "Not specified"

    levels_that_override_global = ["install", "wheel", "download"]
    global_overriding_level = [
        level for level in levels if level in levels_that_override_global
    ]
    if not global_overriding_level:
        return "global"

    if "global" in levels:
        levels.remove("global")
    