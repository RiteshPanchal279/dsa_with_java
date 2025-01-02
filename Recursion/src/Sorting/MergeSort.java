release",
    "fedora-release",
    "gentoo-release",
    "mageia-release",
    "mandrake-release",
    "mandriva-release",
    "mandrivalinux-release",
    "manjaro-release",
    "oracle-release",
    "redhat-release",
    "rocky-release",
    "sl-release",
    "slackware-version",
]

# Base file names to be ignored when searching for distro release file
_DISTRO_RELEASE_IGNORE_BASENAMES = (
    "debian_version",
    "lsb-release",
    "oem-release",
    _OS_RELEASE_BASENAME,
    "system-release",
    "plesk-release",
    "iredmail-release",
    "board-release",
    "ec2_version",
)


def linux_distribution(full_distribution_name: bool = True) -> Tuple[str, str, str]:
    """
    .. deprecated:: 1.6.0

        :func:`distro.linux_distribution()` is deprecated. It should only be
        used as a compatibility shim with Python's
        :py:func:`platform.linux_distribution()`. Please use :func:`distro.id`,
        :func:`distro.version` and :func:`distro.name` instead.

    Return information about the current OS distribution as a tuple
    ``(id_name, version, codename)`` with items as follows:

    * ``id_name``:  If *full_distribution_name* is false, the result of
      :func:`distro.id`. Otherwise, the result of :func:`distro.name`.

    * ``version``:  The result of :func:`distro.version`.

    * ``codename``:  The extra item (usually in par