package com.Altshuler.injectorTest;

import com.Altshuler.provider.Provider;
import com.Altshuler.provider.ProviderImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ProviderImplTest {

    @Test
    void checkProviderInstance(){
        Provider<Object> provider = new ProviderImpl(Object.class);
        assertSame(Object.class, provider.getInstance().getClass());
    }
}
