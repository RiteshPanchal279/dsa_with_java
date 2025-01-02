     auth = auth or None
            host, port = _HOST_PORT_RE.match(host_port).groups()
            if auth and normalize_uri:
                auth = _encode_invalid_chars(auth, USERINFO_CHARS)
            if port == "":
                port = None
        else:
            auth, host, port = None, None, None

        if port is not None:
            port = int(port)
            if not (0 <= port <= 65535):
                raise LocationParseError(url)

        host = _normalize_host(host, scheme)

        if normalize_uri and path:
            path = _remove_path_dot_segments(path)
            path = _encode_invalid_chars(path, PATH_CHARS)
        if normalize_uri and query:
            query = _encode_invalid_chars(query, QUERY_CHARS)
        if normalize_uri and fragment:
            fragment = _encode_invalid_chars(fragment, FRAGMENT_CHARS)

    except (ValueError, AttributeError):
        return six.raise_from(LocationParseError(source_url), None)

    # For the sake of backwards compatibility we put empty
    # string 