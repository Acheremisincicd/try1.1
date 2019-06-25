FROM mcr.microsoft.com/dotnet/core/sdk:2.1 AS build
WORKDIR /Sample

# copy csproj and restore as distinct layers
COPY *.sln .
COPY Sample/*.csproj ./Sample/
RUN dotnet restore

# copy everything else and build app
COPY Sample/. ./Sample/
WORKDIR /Sample/Sample
RUN dotnet publish -c Release -o out


FROM mcr.microsoft.com/dotnet/core/aspnet:2.1 AS runtime
WORKDIR /Sample
COPY --from=build /Sample/Sample/out ./
ENTRYPOINT ["dotnet", "Sample.dll"]
