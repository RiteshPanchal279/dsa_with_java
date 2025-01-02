"
    if error == 0:
        return

    cf_error_string = Security.SecCopyErrorMessageString(error, None)
    output = _cf_string_to_unicode(cf_error_string)
    CoreFoundation.CFRelease(cf_error_string)

    if output is None or output == u"":
        output = u"OSStatus %s" % error

    if exception_class is None:
        exception_class = ssl.SSLError

    raise exception_class(output)


def _cert_array_from_pem(pem_bundle):
    """
    Given a bundle of certs in PEM format, turns them into a CFArray of certs
    that can be used to validate a cert chain.
    """
    # Normalize the PEM bundle's line endings.
    pem_bundle = pem_bundle.replace(b"\r\n", b"\n")

    der_certs = [
        base64.b64decode(match.group(1)) for match in _PEM_CERTS_RE.finditer(pem_bundle)
    ]
    if not der_certs:
        raise ssl.SSLError("No root certificates specified")

    cert_array = CoreFoundation.CFArrayCreateMutable(
        CoreFoundation.