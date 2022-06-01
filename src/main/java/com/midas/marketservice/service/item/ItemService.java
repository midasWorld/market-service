package com.midas.marketservice.service.item;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.marketservice.domain.item.Item;
import com.midas.marketservice.domain.item.ItemRepository;
import com.midas.marketservice.error.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public Item create(Item item) {
		return itemRepository.save(item);
	}

	@Transactional
	public Item modify(Item item) {
		checkArgument(item.getId() != null, "itemId must be provided.");

		return itemRepository.findById(item.getId())
			.map(updateItem -> {
				updateItem.updateItemInfo(item);
				return itemRepository.save(updateItem);
			})
			.orElseThrow(() -> new NotFoundException(Item.class, item.getId()));
	}

	@Transactional
	public void removeStock(Long id, long qty) {
		checkArgument(id != null, "itemId must be provided.");

		Item item = itemRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(Item.class, id));
		
		item.removeStock(qty);
		itemRepository.save(item);
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public Optional<Item> findById(Long id) {
		checkArgument(id != null, "itemId must be provided.");

		return itemRepository.findById(id);
	}
}
