ntityCreateWithCertificate(
                keychain, certificates[0], ctypes.byref(new_identity)
            )
            _assert_no_error(status)
            identities.append(new_identity)

            # We now want to release the original certificate, as we no longer
            # need it.
            CoreFoundation.CFRelease(certificates.pop(0))

        # We now need to build a new CFArray that holds the trust chain.
        trust_chain = CoreFoundation.CFArrayCreateMutable(
            CoreFoundation.kCFAllocatorDefault,
            0,
            ctypes.byref(CoreFoundation.kCFTypeArrayCallBacks),
        )
        for item in itertools.chain(identities, certificates):
            # ArrayAppendValue does a CFRetain on the item. That's fine,
            # because the finally block will release our other refs to them.
            CoreFoundation.CFArrayAppendValue(trust_chain, item)

        return trust_chain
    finally:
        for obj in itertools.chain(identities, certificates):
            CoreFoundation.CFRelease(obj)


TLS_PROTOCOL_VERSIONS = {
    "SSLv2": (0, 2),
    "SSLv3": (3, 0),
    "TLSv1": (3, 1),
    "TLSv1.1": (3, 2),
    "TLSv1.2": (