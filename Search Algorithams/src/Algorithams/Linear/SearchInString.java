 broken.
        """
        assert self._result is not None, "must call resolve() first"

        if not req_set.requirements:
            # Nothing is left to install, so we do not need an order.
            return []

        graph = self._result.graph
        weights = get_topological_weights(graph, set(req_set.requirements.keys()))

        sorted_items = sorted(
            req_set.requirements.items(),
            key=functools.partial(_req_set_item_sorter, weights=weights),
            reverse=True,
        )
        return [ireq for _, ireq in sorted_items]


def get_topological_weights(
    graph: "DirectedGraph[Optional[str]]", requirement_keys: Set[str]
) -> Dict[Optional[str], int]:
    """Assign weights to each node based on how "deep" they are.

    This implementation may