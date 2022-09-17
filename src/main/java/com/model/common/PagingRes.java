package com.model.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingRes {
	private List<Object> items;		// Any collections to be passed in the result
	private long totalItems;		// total items
	private Object item;			// Any object to be passed in the result
}
