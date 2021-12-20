package springboot.rest.libraryapi.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springboot.rest.libraryapi.library.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>{

	@Query("SELECT t FROM Subscription t where t.subscriber_name = :subscriber_name")
	List<Subscription> findAllBySubscriptionName(@Param("subscriber_name") String subcriberName);
	//public List<Subscription> findAllBySubscriptionName(@Param("subscriber_name") Optional<String> subscriber_name);
}
