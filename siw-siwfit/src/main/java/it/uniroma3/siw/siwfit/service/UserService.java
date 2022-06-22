package it.uniroma3.siw.siwfit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwfit.model.User;
import it.uniroma3.siw.siwfit.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void save(User user) {
		this.userRepository.save(user);
	}

	public User findById(Long id) {
		return this.userRepository.findById(id).get();
	}
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	public boolean alreadyExists(User user) {
		return this.userRepository.existsById(user.getId());
	}

	public boolean alreadyExistsByEmail(User user) {
		return this.userRepository.existsByEmail(user.getEmail());
	}
}
