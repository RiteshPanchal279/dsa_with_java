dir = wheel_cache.get_ephem_path_for_link(req.link)
    return cache_dir


def _verify_one(req: InstallRequirement, wheel_path: str) -> None:
    canonical_name = canonicalize_name(req.name or "")
    w = Wheel(os.path.basename(wheel_path))
    if canonicalize_name(w.name) != canonical_name:
        raise InvalidWheelFilename(
            f"Wheel has unexpected file name: expected {canonical_name!r}, "
            f"got {w.name!r}",
        )
    dist = get_wheel_distribution(FilesystemWheel(wheel_path), canonical_name)
    dist_verstr = str(dist.version)
    if canonicalize_version(dist_verstr) != canonicalize_version(w.version):
        raise InvalidWheelFilename(
            f"Wheel has unexpected file name: expected {dist_verstr!r}, "
            f"got {w.version!r}",
        )
    metadat