package fr.epsilonmc.core.modules.exp;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import fr.epsilonmc.api.module.EpsilonModule;
import fr.epsilonmc.core.modules.exp.listener.PlayerExpChangeListener;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@EpsilonModule(name = "exp", listeners = {PlayerExpChangeListener.class})
public class ExpModule {

    @Getter
    private final LoadingCache<String, Integer> playerCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build(new ExpCacheLoader());

}
